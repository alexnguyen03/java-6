import React, {useEffect, useState} from 'react';
import DataTable from 'react-data-table-component';
import {Nav, Row, Tab, Col, Button} from '@themesberg/react-bootstrap';
import dayjs from 'dayjs';
import {DateRangePicker} from '@mui/x-date-pickers-pro/DateRangePicker';
import {AdapterDayjs} from '@mui/x-date-pickers/AdapterDayjs';
import {LocalizationProvider} from '@mui/x-date-pickers/LocalizationProvider';
import {DemoItem} from '@mui/x-date-pickers/internals/demo';
import moment from 'moment-timezone';

const ROOT_URL = 'http://localhost:8080/admin/report';
export default () => {
	const [reports, setReports] = useState([]);
	const [loading, setLoading] = useState(true);
	const [rangeDate, setRangeDate] = useState([dayjs(new Date()), dayjs(new Date())]);

	const columns = [
		{
			name: 'Tên khách hàng',
			selector: (row) => row.user,
			sortable: true,
		},
		{
			name: 'Địa chỉ',
			selector: (row) => row.address,
			sortable: true,
		},
		{
			name: 'Tổng đơn hàng',
			selector: (row) => row.totalOrder,
			sortable: true,
		},
		{
			name: 'Tổng tiền',
			selector: (row) => row.totalPrice.toLocaleString('it-IT', {style: 'currency', currency: 'VND'}),
			sortable: true,
		},
	];
	const conditionalRowStyles = [
		{
			when: (row) => row.totalPrice >= 500000,
			style: {backgroundColor: 'rgb(109, 235, 198)'},
		},
	];
	const getData = async () => {
		try {
			const resp = await fetch(ROOT_URL + '/report-by-user');
			const data = await resp.json();
			{
				setLoading(false);
				console.log(data);
				setReports(data);
			}
		} catch (error) {
			console.log(error);
		}
	};
	const handleResetFilter = () => {
		getData();
		setRangeDate([dayjs(new Date()), dayjs(new Date())]);
	};
	const handleFilter = async () => {
		console.log(moment(rangeDate[0].$d).format('yyyy-MM-DD'));
		console.log(moment(rangeDate[1].$d).format('yyyy-MM-DD'));
		try {
			const resp = await fetch(ROOT_URL + `/report-by-user/filter?startDate= ${moment(rangeDate[0].$d).format('yyyy-MM-DD')}&endDate=${moment(rangeDate[1].$d).format('yyyy-MM-DD')}`);
			const data = await resp.json();
			setLoading(false);
			console.log(data);
			setReports(data);
		} catch (error) {
			console.log(error);
		}
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
				<Col xl={4}>
					<div className='pb-3 mb-2'>
						<Button
							onClick={() => handleFilter()}
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
				data={reports}
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
