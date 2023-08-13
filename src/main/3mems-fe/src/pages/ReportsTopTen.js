import React, {useEffect, useState} from 'react';
import DataTable from 'react-data-table-component';
import {Nav, Row, Tab, Col, Button} from '@themesberg/react-bootstrap';
import dayjs from 'dayjs';
import {DateRangePicker} from '@mui/x-date-pickers-pro/DateRangePicker';
import {AdapterDayjs} from '@mui/x-date-pickers/AdapterDayjs';
import {LocalizationProvider} from '@mui/x-date-pickers/LocalizationProvider';
import {DemoItem} from '@mui/x-date-pickers/internals/demo';

const ROOT_URL = 'http://localhost:8080/admin/report';

export default () => {
	const [topTen, setTopTen] = useState([]);
	const [loading, setLoading] = useState(true);
	const [rangeDate, setRangeDate] = useState([dayjs(new Date()), dayjs(new Date())]);

	const columns = [
		{
			name: 'Danh mục sản phẩm',
			sortable: true,
			selector: (row) => row.category,
		},
		{
			name: 'Tên sản phẩm',
			sortable: true,
			selector: (row) => row.name,
		},
		{
			name: 'Tổng tiền',
			selector: (row) => row.price.toLocaleString('it-IT', {style: 'currency', currency: 'VND'}),
			sortable: true,
		},
		{
			name: 'Số lượng bán',
			selector: (row) => row.quantity,
			sortable: true,
		},
	];
	const conditionalRowStyles = [
		{
			when: (row) => row.price >= 500000,
			style: {backgroundColor: 'rgb(109, 235, 198)'},
		},
	];
	const getData = async () => {
		try {
			const resp = await fetch(ROOT_URL + '/report-top-ten');
			const data = await resp.json();
			setLoading(false);
			setTopTen(data);
		} catch (error) {
			console.log(error);
		}
	};
	const handleResetFilter = () => {
		getData();
		setRangeDate([dayjs(new Date()), dayjs(new Date())]);
	};
	useEffect(() => {
		getData();
	}, []);
	return (
		<>
			<Row>
				<Col xl={4}>
					<LocalizationProvider dateAdapter={AdapterDayjs}>
						<DemoItem
							label='Ngày bắt đầu - Ngày kết thúc'
							component='DateRangePicker'>
							<DateRangePicker
								value={rangeDate}
								onChange={(newValue) => setRangeDate(newValue)}
							/>
						</DemoItem>
					</LocalizationProvider>
				</Col>
				<Col xl={2}>
					<div className='pb-3 mb-2'>
						<Button
							// onClick={() => handleUpdateCoupon()}
							variant='info'
							size='sm'
							className='mt-5'
							// disabled={!update}
						>
							Xem thống kê
						</Button>
						<Button
							onClick={handleResetFilter}
							variant='info'
							size='sm'
							className='mt-5 ms-3'
							// disabled={!update}
						>
							X
						</Button>
					</div>
				</Col>
			</Row>
			<DataTable
				// expandableRows
				columns={columns}
				data={topTen}
				striped
				pagination
				highlightOnHover
				progressPending={loading}
				fixedHeader
				conditionalRowStyles={conditionalRowStyles}
				fixedHeaderScrollHeight='500px'
			/>
		</>
	);
};
